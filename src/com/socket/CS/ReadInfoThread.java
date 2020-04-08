package com.socket.CS;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadInfoThread implements Runnable {

    private ObjectInputStream in;
    private boolean flag=true;

    public ReadInfoThread(ObjectInputStream in) {
        this.in = in;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {

        try {

            while (flag){
                Message message = (Message) in.readObject();
                System.out.println("["+message.getFrom()+"]对我说："+message.getInfo());
            }
            if (in!=null){
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
