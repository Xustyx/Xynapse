package com.xsty.xynapse.service;

import com.xsty.xynapse.model.Dendrite;
import com.xsty.xynapse.model.Neuron;

import java.util.List;

/**
 * Created by XST on 03/09/2016.
 */
public interface NeuronService {

    public List<Neuron> getNeuronTransmitters(Neuron neuron);
    public List<Neuron> getNeuronReceptors(Neuron neuron);

    public void connect(Neuron from, Neuron to);
    public void disconnect(Neuron from, Neuron to);
    public double think(Neuron neuron);

    public void setSignal(Neuron neuron, double signal);
    public double getSignal(Neuron neuron);

    public Dendrite addDendrite(Neuron neuron);
    //private void removeDendrite(Neuron neuron, Dendrite dendrite);
}
