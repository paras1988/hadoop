package com.CustomFormat;

import com.google.common.collect.ComparisonChain;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Key_Value implements WritableComparable<Key_Value> {
    private String x;
    private String y;

    public String getX() {
        return x;
    }
    public void setX(String x) {
        this.x = x;
    }
    public String getY() {
        return y;
    }
    public void setY(String y) {
        this.y = y;
    }

    public Key_Value(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public Key_Value() {
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(x);
        dataOutput.writeUTF(y);
    }

    public void readFields(DataInput dataInput) throws IOException {
        x = dataInput.readUTF();
        y = dataInput.readUTF();
    }

    public int compareTo(Key_Value key_value) {
        return ComparisonChain.start().compare(this.y,key_value.y).compare(this.x,key_value.x).result();
    }

    public boolean equals(Object o1) {
        if (!(o1 instanceof Key_Value)) {
            return false;
        }
        Key_Value other = (Key_Value)o1;
        return this.x == other.x && this.y == other.y;
    }
    @Override
    public String toString() {
        return x.toString()+","+y.toString();
    }
}
