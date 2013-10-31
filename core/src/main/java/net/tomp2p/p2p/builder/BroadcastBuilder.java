/*
 * Copyright 2012 Thomas Bocek
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package net.tomp2p.p2p.builder;

import java.util.Map;

import net.tomp2p.connection2.DefaultConnectionConfiguration;
import net.tomp2p.message.DataMap;
import net.tomp2p.message.Message;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.Number480;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.Data;

public class BroadcastBuilder extends DefaultConnectionConfiguration {

    private final Peer peer;
    
    private final Number160 messageKey;

    private Map<Number480, Data> dataMap;

    private Boolean isUDP;
    
    private int hopCounter;

    public BroadcastBuilder(Peer peer, Number160 messageKey) {
        this.peer = peer;
        this.messageKey = messageKey;
    }

    public void start() {
        Message message = new Message();
        if (isUDP == null) {
            // not set, decide based on the data
            if (dataMap == null) {
                setIsUDP(true);
            } else {
                setIsUDP(false);
                message.setDataMap(new DataMap(dataMap));
            }
        }
        
        message.setKey(messageKey);
        message.setInteger(0);
        
        peer.getBroadcastRPC().broadcastHandler().receive(message);
    }
    
    public Number160 messageKey() {
        return messageKey;
    }

    public Map<Number480, Data> dataMap() {
        return dataMap;
    }

    public BroadcastBuilder dataMap(Map<Number480, Data> dataMap) {
        this.dataMap = dataMap;
        return this;
    }

    public boolean isUDP() {
        if (isUDP == null) {
            return false;
        }
        return isUDP;
    }

    public BroadcastBuilder setIsUDP(boolean isUDP) {
        this.isUDP = isUDP;
        return this;
    }
    
    public int hopCounter() {
        return hopCounter;
    }
    
    public BroadcastBuilder hopCounter(int hopCounter) {
        this.hopCounter = hopCounter;
        return this;
    }

    public PeerAddress remotePeer() {
        return peer.getPeerAddress();
    }
}
