package com.snm.trade.stock;

import org.junit.Assert;
import org.junit.Test;

public class Comparator {

    @Test
    public void compare() {

    }

    public boolean matchDeviceIdS12(String npamsDeviceId, String s12DeviceId) {
        String[] npamsStra = npamsDeviceId.split("\\s+");
        String np1 = npamsStra[1].replaceAll("\\D+", "");
        String np2 = npamsStra[2].replaceAll("\\D+", "");
        String np3 = npamsStra[3].replaceAll("\\D+", "");
        String[] s12Stra = s12DeviceId.split("\\s+");
        String sp0 = s12Stra[0].replaceAll("\\D+", "");
        String sp3 = s12Stra[3].replaceAll("\\D+", "");
        System.out.println("sp0: " + sp0);
        System.out.println("sp3: " + sp3);
        if ((np1 + np2).equals(sp0) && np3.equals(sp3)) {
            return true;
        }
        return false;
    }

    public boolean matchDeviceIdAXE(String npamsDeviceId, String axeDeviceId) {
        String[] npamsStra = npamsDeviceId.split("\\s+");
        String np = (npamsStra[2] + npamsStra[3]).replaceAll("[^A-Za-z0-9]", "");
        String sp  = axeDeviceId.replaceAll("[^A-Za-z0-9]", "");
        System.out.println("np: " + np);
        System.out.println("sp: " + sp);
        if (np.equals(sp)) {
            return true;
        }
        return false;
    }
}
