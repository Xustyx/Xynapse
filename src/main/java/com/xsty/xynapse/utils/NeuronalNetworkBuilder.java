package com.xsty.xynapse.utils;

import com.xsty.xynapse.model.NeuronalNetwork;
import com.xsty.xynapse.model.Neuron;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XST on 04/09/2016.
 */
public class NeuronalNetworkBuilder {

    private NeuronalNetwork neuronalNetwork;

    public NeuronalNetworkBuilder(){
        this.neuronalNetwork = new NeuronalNetwork();
    }

    public NeuronalNetworkBuilder addInputNeuron(){
        Neuron input = NeuronFactory.createSensorNeuron();
        getInputLayer().add(input);

        return this;
    }

    public NeuronalNetworkBuilder addOutputNeuron(){
        Neuron output = NeuronFactory.createMotorNeuron();
        getOutputLayer().add(output);

        return this;
    }

    public NeuronalNetworkBuilder addHiddenLayer(int size){
        List<Neuron> hiddenLayer = createHiddenLayer(size);
        getHiddenLayers().add(hiddenLayer);

        return this;
    }

    public NeuronalNetwork build(){
        return this.neuronalNetwork;
    }


    private List<Neuron> getInputLayer(){
        return this.neuronalNetwork.getInputLayer();
    }

    private List<Neuron> getOutputLayer(){
        return this.neuronalNetwork.getOutputLayer();
    }

    private List<List<Neuron>> getHiddenLayers(){
        return this.neuronalNetwork.getHiddenLayers();
    }

    private List<Neuron> createHiddenLayer(int size){
        List<Neuron> hiddenLayer = new ArrayList<Neuron>();

        for(int i = 0; i < size; i++){
            Neuron inter = NeuronFactory.createInterNeuron();
            hiddenLayer.add(inter);
        }

        return hiddenLayer;
    }

}
