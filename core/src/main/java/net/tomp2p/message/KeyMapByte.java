package net.tomp2p.message;

import java.util.Map;

import net.tomp2p.peers.Number480;
import net.tomp2p.utils.Utils;

public class KeyMapByte {

    private final Map<Number480, Byte> keysMap;
    public KeyMapByte(Map<Number480, Byte> keysMap) {
        this.keysMap = keysMap;
    }
    
    public Map<Number480, Byte> keysMap() {
        return keysMap;
    }

    public int size() {
        return keysMap.size();
    }

    public void put(Number480 key, Byte value) {
        keysMap.put(key, value);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof KeyMapByte)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        KeyMapByte k = (KeyMapByte) obj;
        boolean test1 = Utils.isSameSets(k.keysMap.keySet(), keysMap.keySet());
        boolean test2 = Utils.isSameSets(k.keysMap.values(), keysMap.values());
        return test1 && test2;
    }
}
