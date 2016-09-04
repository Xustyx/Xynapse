package com.xsty.xynapse.service;

import com.xsty.xynapse.model.NeuronalNetwork;
/**
 * Created by XST on 04/09/2016.
 */
public interface NeuronalNetworkService {
    public boolean connect(NeuronalNetwork neuronalNetwork);
    public double[] think(NeuronalNetwork neuronalNetwork);

    public void setInputSignal(NeuronalNetwork neuronalNetwork, double signal);
    public void setInputSignals(NeuronalNetwork neuronalNetwork, double[] signals);

    public double getOutputSignal(NeuronalNetwork neuronalNetwork);
    public double[] getOutputSignals(NeuronalNetwork neuronalNetwork);

}
