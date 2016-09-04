package com.xsty.xynapse.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XST on 03/09/2016.
 */
public class Axon {

    private double signal;

    //Links
    private Neuron selfNeuron;
    private List<Dendrite> dendrites;

    public Axon(Neuron selfNeuron){
        this.selfNeuron = selfNeuron;
        this.dendrites = new ArrayList<Dendrite>();
        this.signal = 0;
    }

    public List<Dendrite> getDendrites() {
        return dendrites;
    }

    public void setDendrites(List<Dendrite> dendrites) {
        this.dendrites = dendrites;
    }

    public double getSignal() {
        return signal;
    }

    public void setSignal(double signal) {
        this.signal = signal;
    }

    public Neuron getSelfNeuron() {
        return selfNeuron;
    }

    public void setSelfNeuron(Neuron selfNeuron) {
        this.selfNeuron = selfNeuron;
    }
}
