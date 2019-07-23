package com.pricing;

import java.util.List;
import java.util.Map;

public interface Discount {
    List<Savings> apply(Map<String, List<Item>> basket);
}
