package Temp;

public class FreshPromotion {
    private static int freshPromotion(String[][] codeList, String[] shoppingCart) {
//        Start at 0 index for both the code list and shopping cart.
        int cartIdx = 0, codeIdx = 0;
        while (cartIdx < shoppingCart.length && codeIdx < codeList.length) {
            String cur = shoppingCart[cartIdx];
//            If the first fruit of the codeList is anything or if it matches the current fruit at the cart idx.
            if ((codeList[codeIdx][0].equals("anything") || codeList[codeIdx][0].equals(cur)) && hasOrder(shoppingCart, cartIdx, codeList[codeIdx])) {
                cartIdx += codeList[codeIdx++].length;
            } else {
                cartIdx++;
            }
        }
//        If the all the codeList is present then return 1, else 0.
        return codeIdx == codeList.length ? 1 : 0;
    }

    private static boolean hasOrder(String[] shoppingCart, int idx, String[] order) {
//        Loop through the codeList to check if the fruits are in order.
        for (String s : order) {
            if (idx < shoppingCart.length && (s.equals("anything") || shoppingCart[idx].equals(s))) {
                idx++;
            } else {
                return false;
            }
        }
        return true;
    }
}
