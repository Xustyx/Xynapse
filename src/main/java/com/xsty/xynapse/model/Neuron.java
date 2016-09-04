package com.xsty.xynapse.model;

import com.xsty.xynapse.utils.NeuronType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XST on 03/09/2016.
 */
public class Neuron {

    private NeuronType type;

    //Links
    private List<Dendrite> dendrites;
    private Axon axon;

    public Neuron() {
        this.dendrites = new ArrayList<Dendrite>();
        this.axon = new Axon(this);
    }

    public Axon getAxon() {
        return axon;
    }

    public void setAxon(Axon axon) {
        this.axon = axon;
    }

    public List<Dendrite> getDendrites() {
        return dendrites;
    }

    public void setDendrites(List<Dendrite> dendrites) {
        this.dendrites = dendrites;
    }

    public NeuronType getType() {
        return type;
    }

    public void setType(NeuronType type) {
        this.type = type;
    }
}
