package com.xsty.xynapse.service;

import com.xsty.xynapse.model.Axon;
import com.xsty.xynapse.model.Dendrite;
import com.xsty.xynapse.model.Neuron;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XST on 03/09/2016.
 */
@Service
public class AxonServiceImpl implements AxonService {

    @Override
    public List<Neuron> getNeuronReceptors(Axon axon) {
        List<Neuron> neurons = new ArrayList<Neuron>();

        for(Dendrite dendrite: axon.getDendrites()){
            neurons.add(dendrite.getSelfNeuron());
        }

        return neurons;
    }

    @Override
    public void connect(Axon axon, Dendrite dendrite) {
        dendrite.setAxon(axon);
        axon.getDendrites().add(dendrite);
    }

    @Override
    public void disconnect(Axon axon, Dendrite dendrite) {
        dendrite.setAxon(null);
        axon.getDendrites().remove(dendrite);
    }
}
