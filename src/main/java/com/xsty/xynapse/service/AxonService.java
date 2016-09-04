package com.xsty.xynapse.service;

import com.xsty.xynapse.model.Axon;
import com.xsty.xynapse.model.Dendrite;
import com.xsty.xynapse.model.Neuron;

import java.util.List;

/**
 * Created by XST on 03/09/2016.
 */
public interface AxonService {
    public List<Neuron> getNeuronReceptors(Axon axon);

    public void connect(Axon axon, Dendrite dendrite);
    public void disconnect(Axon axon, Dendrite dendrite);
}
