package com.xsty.xynapse.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by XST on 04/09/2016.
 */
@Service
public class XynapseUtils {

    private Random random;

    public XynapseUtils(){
        this.random = new Random();
    }

    public double sigmoid(double x){
        return 1 / ( 1 + Math.exp(-x) );
    }

    public double sigmoidDerivative(double x){
        double sigmoidValue = sigmoid(x);
        return sigmoidValue * ( 1 - sigmoidValue );
    }

    public Random getRandom() {
        return this.random;
    }

    public double randomDouble(double min, double max){
        return min + (max - min) * random.nextDouble();
    }
}
