package com.xsty.xynapse.service;

import com.xsty.xynapse.model.Axon;
import com.xsty.xynapse.model.Dendrite;
import com.xsty.xynapse.model.Neuron;

/**
 * Created by XST on 03/09/2016.
 */
public interface DendriteService {
    public Neuron getNeuronTransmitter(Dendrite dendrite);
    public double processSignal(Dendrite dendrite);
    public double getRawSignal(Dendrite dendrite);
}
