package com.example.demo.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomStandardShardingStrategy implements PreciseShardingAlgorithm<Integer>, RangeShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        int length=collection.size();

        for (String s:collection){
            if (s.endsWith(preciseShardingValue.getValue()%length+"")){
                return s;
            }
        }

        return null;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        Range<Integer> range=rangeShardingValue.getValueRange();
        int min=range.lowerEndpoint();
        int max=range.upperEndpoint();

        Set<String> result=new HashSet<>();
        int length=collection.size();
        for (int i=min;i<=max;i++){
            for (String s:collection){
                if (s.endsWith(i%length+"")){
                    result.add(s);
                }
            }

            if (result.size()==length){
                break;
            }
        }

        return result;
    }
}