package com.github.nwpeckham.kenosabe.odds;

import org.apache.commons.math3.distribution.*;

/**
 * Created by Nathan on 3/9/2015.
 */
public class PayTable {
    private HypergeometricDistribution _dist;
    private int _numPicked;
    private int[] _payouts = {0,0,13};

    public PayTable(int numPicked){
        _numPicked = numPicked;
        _dist = new HypergeometricDistribution(80,20, _numPicked);
    }

    public double getOdds(int numCorrect) {
        return _dist.probability(numCorrect);
    }

    public double getExpectedPayout(int numCorrect, double wagerAmount){
        double amount = 0;
        for (int numCaught = 0; numCaught <= _numPicked; numCaught++) {
            amount += (getOdds(numCaught) * _payouts[numCaught]);
        }
        return amount;
    }

}
