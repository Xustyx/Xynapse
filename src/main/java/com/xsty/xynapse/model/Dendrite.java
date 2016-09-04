package com.xsty.xynapse.model;

/**
 * Created by XST on 03/09/2016.
 */
public class Dendrite {

    private double weigth;

    //Links
    private Axon axon;
    private Neuron selfNeuron;

    public Dendrite(Neuron selfNeuron){
        this.selfNeuron = selfNeuron;
        this.weigth = 0;
    }

    public Axon getAxon() {
        return axon;
    }

    public void setAxon(Axon axon) {
        this.axon = axon;
    }

    public Neuron getSelfNeuron() {
        return selfNeuron;
    }

    public void setSelfNeuron(Neuron selfNeuron) {
        this.selfNeuron = selfNeuron;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }
}
