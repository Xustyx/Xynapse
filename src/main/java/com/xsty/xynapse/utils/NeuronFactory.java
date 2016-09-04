package com.xsty.xynapse.utils;

import com.xsty.xynapse.model.Neuron;

/**
 * Created by XST on 04/09/2016.
 */
public class NeuronFactory {

    public static Neuron createMotorNeuron(){
        return createNeuron(NeuronType.Motor);
    }

    public static Neuron createSensorNeuron(){
        return createNeuron(NeuronType.Sensory);
    }

    public static Neuron createInterNeuron(){
        return createNeuron(NeuronType.Inter);
    }

    public static Neuron createNeuron(NeuronType type){
        Neuron neuron = new Neuron();
        neuron.setType(type);

        return neuron;
    }

}
