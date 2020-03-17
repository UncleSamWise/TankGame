package com.cs240.tankgame;

import java.util.LinkedList;
import java.util.Queue;

public class MoveQueue {

    private Queue<Integer> moveQueue;

    public MoveQueue(int move) {
        this.moveQueue = new LinkedList<>();
        this.moveQueue.add(move);
    }

    public int giveTurn() {
        int move = moveQueue.remove();
        return move;
    }
}
