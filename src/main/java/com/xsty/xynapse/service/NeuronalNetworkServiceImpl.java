package com.xsty.xynapse.service;

import com.xsty.xynapse.model.Neuron;
import com.xsty.xynapse.model.NeuronalNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XST on 04/09/2016.
 */
@Service
public class NeuronalNetworkServiceImpl implements NeuronalNetworkService {

    private NeuronService neuronService;

    @Autowired
    public NeuronalNetworkServiceImpl(NeuronService neuronService){
        this.neuronService = neuronService;
    }

    @Override
    public boolean connect(NeuronalNetwork neuronalNetwork){
       return connectInputLayer(neuronalNetwork)
               && connectHiddenLayers(neuronalNetwork)
               && connectOutputLayer(neuronalNetwork);
    }

    @Override
    public double[] think(NeuronalNetwork neuronalNetwork) {
        thinkInputLayer(neuronalNetwork);
        thinkHiddenLayers(neuronalNetwork);
        return thinkOutputLayer(neuronalNetwork);
    }

    @Override
    public void setInputSignal(NeuronalNetwork neuronalNetwork, double signal) {
        List<Neuron> inputLayer = neuronalNetwork.getInputLayer();

        if(inputLayer.size() > 0){
            neuronService.setSignal(inputLayer.get(0), signal);
        }
    }

    @Override
    public void setInputSignals(NeuronalNetwork neuronalNetwork, double[] signals) {
        List<Neuron> inputLayer = neuronalNetwork.getInputLayer();
        int inputLayerSize = inputLayer.size();
        int signalsSize = signals.length;
        int minSize = signalsSize > inputLayerSize ? signalsSize : inputLayerSize;

        for(int i = 0; i < minSize; i++){
            Neuron input = inputLayer.get(i);
            neuronService.setSignal(input, signals[i]);
        }

    }

    @Override
    public double getOutputSignal(NeuronalNetwork neuronalNetwork) {
        List<Neuron> outputLayer = neuronalNetwork.getOutputLayer();
        double signal = 0;

        if(outputLayer.size() > 0){
            signal = neuronService.getSignal(outputLayer.get(0));
        }

        return signal;
    }

    @Override
    public double[] getOutputSignals(NeuronalNetwork neuronalNetwork) {
        List<Neuron> outputLayer = neuronalNetwork.getOutputLayer();
        int outputLayerSize = outputLayer.size();
        double[] signals = new double[outputLayerSize];

        for(int i = 0; i < outputLayerSize; i++){
            Neuron output = outputLayer.get(i);
            signals[i] = neuronService.getSignal(output);
        }

        return signals;
    }

    private boolean connectInputLayer(NeuronalNetwork neuronalNetwork){
        List<List<Neuron>> hiddenLayers = neuronalNetwork.getHiddenLayers();
        int size = hiddenLayers.size();
        boolean canConnect = size > 0;

        if(canConnect){
            List<Neuron> froms = neuronalNetwork.getInputLayer();
            List<Neuron> tos = hiddenLayers.get(0);

            canConnect = connectLayers(froms, tos);
        }

        return canConnect;
    }

    private boolean connectHiddenLayers(NeuronalNetwork neuronalNetwork) {
        List<List<Neuron>> hiddenLayers = neuronalNetwork.getHiddenLayers();
        int endHiddenLayerIndex = hiddenLayers.size() - 1;
        boolean canConnect = true;
        int i = 0;

        while (canConnect && i < endHiddenLayerIndex){
            List<Neuron> froms = hiddenLayers.get(i);
            List<Neuron> tos = hiddenLayers.get(i + 1);

            canConnect = connectLayers(froms, tos);

            i++;
        }

        return canConnect;
    }

    private boolean connectOutputLayer(NeuronalNetwork neuronalNetwork){
        List<List<Neuron>> hiddenLayers = neuronalNetwork.getHiddenLayers();
        int size = hiddenLayers.size();
        boolean canConnect = size > 0;

        if(canConnect){
            List<Neuron> tos = neuronalNetwork.getOutputLayer();
            List<Neuron> froms = hiddenLayers.get(size - 1);

            canConnect = connectLayers(froms, tos);
        }

        return canConnect;
    }

    private boolean connectLayers(List<Neuron> froms, List<Neuron> tos){
        for(Neuron from: froms){
            for(Neuron to: tos){
                neuronService.connect(from, to);
            }
        }

        return froms.size() > 0 && tos.size() > 0;
    }

    private double[] thinkInputLayer(NeuronalNetwork neuronalNetwork){
        return thinkLayer(neuronalNetwork.getInputLayer());
    }

    private double[][] thinkHiddenLayers(NeuronalNetwork neuronalNetwork){
        List<List<Neuron>> hiddenLayers = neuronalNetwork.getHiddenLayers();
        int hiddenLayersSize = hiddenLayers.size();
        double[][] signals = new double[hiddenLayersSize][];

        for(int i = 0; i < hiddenLayersSize; i++){
            List<Neuron> layer = hiddenLayers.get(i);
            signals[i] = thinkLayer(layer);
        }

        return signals;
    }

    private double[] thinkOutputLayer(NeuronalNetwork neuronalNetwork){
        return thinkLayer(neuronalNetwork.getOutputLayer());
    }

    private double[] thinkLayer(List<Neuron> layer){
        int layerSize = layer.size();
        double[] signals = new double[layerSize];

        for(int i = 0; i < layerSize; i++){
            signals[i] = neuronService.think(layer.get(i)); //Threads ???
        }

        return signals;
    }
}
