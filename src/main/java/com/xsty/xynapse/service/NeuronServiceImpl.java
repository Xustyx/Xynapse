package com.xsty.xynapse.service;

import com.xsty.xynapse.model.Axon;
import com.xsty.xynapse.model.Dendrite;
import com.xsty.xynapse.model.Neuron;
import com.xsty.xynapse.utils.NeuronType;
import com.xsty.xynapse.utils.XynapseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XST on 03/09/2016.
 */
@Service
public class NeuronServiceImpl implements NeuronService {

    private AxonService axonService;
    private DendriteService dendriteService;

    private XynapseUtils xynapseUtils;

    @Autowired
    public NeuronServiceImpl(AxonService axonService, DendriteService dendriteService, XynapseUtils xynapseUtils){
        this.axonService = axonService;
        this.dendriteService = dendriteService;
        this.xynapseUtils = xynapseUtils;
    }

    @Override
    public List<Neuron> getNeuronTransmitters(Neuron neuron) {
        List<Neuron> transmitters = new ArrayList<Neuron>();

        for(Dendrite dendrite: neuron.getDendrites()){
            Neuron transmitter = dendriteService.getNeuronTransmitter(dendrite);
            transmitters.add(transmitter);
        }

        return transmitters;
    }

    @Override
    public List<Neuron> getNeuronReceptors(Neuron neuron) {
        return axonService.getNeuronReceptors(neuron.getAxon());
    }

    @Override
    public void connect(Neuron from, Neuron to) {
        Axon axon = from.getAxon();
        Dendrite dendrite = addDendrite(to);

        axonService.connect(axon, dendrite);
    }

    @Override
    public void disconnect(Neuron from, Neuron to) {
        Dendrite dendrite = null;

        for(Dendrite dentriteTo : to.getDendrites()){
            Neuron neuronTo = dendriteService.getNeuronTransmitter(dentriteTo);
            if(neuronTo == from){
                dendrite = dentriteTo;
                break;
            }
        }

        if(dendrite != null){
            Axon axon = from.getAxon();
            axonService.disconnect(axon, dendrite);
            removeDendrite(to, dendrite);
        }
    }

    @Override
    public double think(Neuron neuron) {
        List<Dendrite> dendrites = neuron.getDendrites();
        double signal = 0;

        for (Dendrite dendrite : neuron.getDendrites()) {
            signal += dendriteService.processSignal(dendrite);
        }

        if(neuron.getType() != NeuronType.Sensory) {
            signal = xynapseUtils.sigmoid(signal);
        } else {
            if(dendrites.isEmpty()) {
                signal = getSignal(neuron);
            }
        }

        setSignal(neuron, signal);

        return signal;
    }

    @Override
    public void setSignal(Neuron neuron, double signal) {
        neuron.getAxon().setSignal(signal);
    }

    @Override
    public double getSignal(Neuron neuron) {
        return neuron.getAxon().getSignal();
    }

    @Override
    public Dendrite addDendrite(Neuron selfNeuron){
        Dendrite dendrite = new Dendrite(selfNeuron);

        double rndWeigth = xynapseUtils.randomDouble(-0.5,0.5);
        dendrite.setWeigth(rndWeigth);

        selfNeuron.getDendrites().add(dendrite);

        return dendrite;
    }

    private void removeDendrite(Neuron selfNeuron, Dendrite dendrite){
       if(selfNeuron.getDendrites().remove(dendrite)){
           dendrite.setSelfNeuron(null);
       }
    }

}
