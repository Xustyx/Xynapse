package com.xsty.xynapse.service;

import com.xsty.xynapse.model.Axon;
import com.xsty.xynapse.model.Dendrite;
import com.xsty.xynapse.model.Neuron;
import org.springframework.stereotype.Service;

/**
 * Created by XST on 03/09/2016.
 */
@Service
public class DendriteServiceImpl implements DendriteService{

    @Override
    public Neuron getNeuronTransmitter(Dendrite dendrite) {
        return dendrite.getAxon().getSelfNeuron();
    }

    @Override
    public double processSignal(Dendrite dendrite) {
        double signal;

        switch (dendrite.getSelfNeuron().getType()){
            case Inter:
            case Motor:
                signal = dendrite.getWeigth() * getRawSignal(dendrite);
                break;
            case Sensory:
                signal = getRawSignal(dendrite);
                break;
            default:
                signal = 0;
                break;
        }

        return signal;
    }

    @Override
    public double getRawSignal(Dendrite dendrite) {
        return dendrite.getAxon().getSignal();
    }
}
