package com.xsty.xynapse;

import com.xsty.xynapse.model.Neuron;
import com.xsty.xynapse.model.NeuronalNetwork;
import com.xsty.xynapse.service.NeuronService;
import com.xsty.xynapse.service.NeuronalNetworkService;
import com.xsty.xynapse.utils.NeuronType;
import com.xsty.xynapse.utils.NeuronalNetworkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class XynapseApplication {

    @Autowired
    private NeuronService neuronService;

    @Autowired
    private NeuronalNetworkService neuronalNetworkService;

	public static void main(String[] args) {
        SpringApplication.run(XynapseApplication.class, args);
	}

    @PostConstruct
    void init(){
        System.out.println("Start work.");
        fase2();
    }

    //Manual network test.
    void fase2(){
        double[] signals;
        double signal;

        NeuronalNetworkBuilder neuronalNetworkBuilder = new NeuronalNetworkBuilder();
        NeuronalNetwork neuronalNetwork = neuronalNetworkBuilder
                .addInputNeuron()
                .addOutputNeuron()
                .addHiddenLayer(2)
                .build();

        if(neuronalNetworkService.connect(neuronalNetwork)) {
            signals = neuronalNetworkService.think(neuronalNetwork);

            //Manual assertion
            signal = neuronalNetworkService.getOutputSignal(neuronalNetwork);

            for (int i = 0; i < signals.length; i++) {
                System.out.println("Signal[" + i + "] =>" + signals[i]);
            }

            System.out.println("Signal[0] =>" + signal);
        } else {
            System.out.println("Can't connect this system.");
        }
    }

    //Manual model test.
    void fase1(){
        Neuron sensory = new Neuron();
        sensory.setType(NeuronType.Sensory);

        Neuron sensory2 = new Neuron();
        sensory2.setType(NeuronType.Sensory);

        Neuron inter = new Neuron();
        inter.setType(NeuronType.Inter);

        neuronService.connect(sensory, inter);
        neuronService.connect(sensory, sensory2);

        sensory.getAxon().setSignal(1);

        double iv = neuronService.think(inter);
        double s2v= neuronService.think(sensory2);
        double sv = neuronService.think(sensory);
    }
}
