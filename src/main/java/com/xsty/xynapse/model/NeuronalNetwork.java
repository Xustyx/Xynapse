package com.xsty.xynapse.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XST on 04/09/2016.
 */
public class NeuronalNetwork {

    private List<Neuron> inputLayer;
    private List<List<Neuron>> hiddenLayers;
    private List<Neuron> outputLayer;

    public NeuronalNetwork(){
        this.inputLayer = new ArrayList<Neuron>();
        this.hiddenLayers = new ArrayList<List<Neuron>>();
        this.outputLayer = new ArrayList<Neuron>();
    }

    public List<List<Neuron>> getHiddenLayers() {
        return hiddenLayers;
    }

    public void setHiddenLayers(List<List<Neuron>> hiddenLayers) {
        this.hiddenLayers = hiddenLayers;
    }

    public List<Neuron> getInputLayer() {
        return inputLayer;
    }

    public void setInputLayer(List<Neuron> inputLayer) {
        this.inputLayer = inputLayer;
    }

    public List<Neuron> getOutputLayer() {
        return outputLayer;
    }

    public void setOutputLayer(List<Neuron> outputLayer) {
        this.outputLayer = outputLayer;
    }
}
