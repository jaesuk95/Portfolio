package com.portfolio.domain.model.delivery;

import org.apache.commons.lang3.Range;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeliveryFeeByZipcode {
    public List<Range> getZipcodeRange() {
        Range<Integer> district_jeju = Range.between(63000, 63644);

        Range<Integer> district_incheon_1 = Range.between(22386, 22388);
        Range<Integer> district_incheon_2 = Range.between(23004, 23010);
        Range<Integer> district_incheon_3 = Range.between(23100, 23116);
        Range<Integer> district_incheon_4 = Range.between(23124, 23136);

        List<Range> incheonDistricts = new ArrayList<>();
        incheonDistricts.add(district_incheon_1);
        incheonDistricts.add(district_incheon_2);
        incheonDistricts.add(district_incheon_3);
        incheonDistricts.add(district_incheon_4);

        Range<Integer> district_chungnam_1 = Range.is(31708);
        Range<Integer> district_chungnam_2 = Range.is(32133);
        Range<Integer> district_chungnam_3 = Range.is(33411);

        List<Range> chungNamDistricts = new ArrayList<>();
        chungNamDistricts.add(district_chungnam_1);
        chungNamDistricts.add(district_chungnam_2);
        chungNamDistricts.add(district_chungnam_3);

        Range<Integer> district_gyeongsang_1 = Range.between(40200, 40240);
        Range<Integer> district_gyeongsang_2 = Range.between(46768, 46771);
        Range<Integer> district_gyeongsang_3 = Range.between(52570, 52571);
        Range<Integer> district_gyeongsang_4 = Range.between(53031, 53033);
        Range<Integer> district_gyeongsang_5 = Range.between(53089, 53104);
        Range<Integer> district_gyeongsang_6 = Range.is(54000);

        List<Range> gyeongsangDistricts = new ArrayList<>();
        gyeongsangDistricts.add(district_gyeongsang_1);
        gyeongsangDistricts.add(district_gyeongsang_2);
        gyeongsangDistricts.add(district_gyeongsang_3);
        gyeongsangDistricts.add(district_gyeongsang_4);
        gyeongsangDistricts.add(district_gyeongsang_5);
        gyeongsangDistricts.add(district_gyeongsang_6);

        Range<Integer> district_jeonra_1 = Range.between(56347, 56349);
        Range<Integer> district_jeonra_2 = Range.between(57068, 57069);
        Range<Integer> district_jeonra_3 = Range.between(58760, 58762);
        Range<Integer> district_jeonra_4 = Range.between(58800, 58810);
        Range<Integer> district_jeonra_5 = Range.between(58816, 58818);
        Range<Integer> district_jeonra_6 = Range.is(28826);
        Range<Integer> district_jeonra_7 = Range.between(58828, 58866);
        Range<Integer> district_jeonra_8 = Range.between(58953, 58958);
        Range<Integer> district_jeonra_9 = Range.between(59102, 59103);
        Range<Integer> district_jeonra_10 = Range.is(59106);
        Range<Integer> district_jeonra_11 = Range.is(59127);
        Range<Integer> district_jeonra_12 = Range.is(59129);
        Range<Integer> district_jeonra_13 = Range.between(59137, 59166);
        Range<Integer> district_jeonra_14 = Range.is(59650);
        Range<Integer> district_jeonra_15 = Range.is(59766);
        Range<Integer> district_jeonra_16 = Range.between(59781, 59790);

        List<Range> jeonraDistricts = new ArrayList<>();
        jeonraDistricts.add(district_jeonra_1);
        jeonraDistricts.add(district_jeonra_2);
        jeonraDistricts.add(district_jeonra_3);
        jeonraDistricts.add(district_jeonra_4);
        jeonraDistricts.add(district_jeonra_5);
        jeonraDistricts.add(district_jeonra_6);
        jeonraDistricts.add(district_jeonra_7);
        jeonraDistricts.add(district_jeonra_8);
        jeonraDistricts.add(district_jeonra_9);
        jeonraDistricts.add(district_jeonra_10);
        jeonraDistricts.add(district_jeonra_11);
        jeonraDistricts.add(district_jeonra_12);
        jeonraDistricts.add(district_jeonra_13);
        jeonraDistricts.add(district_jeonra_14);
        jeonraDistricts.add(district_jeonra_15);
        jeonraDistricts.add(district_jeonra_16);


        List<Range> ranges = new ArrayList<>();
        ranges.add(district_jeju);
        ranges.addAll(incheonDistricts);
        ranges.addAll(chungNamDistricts);
        ranges.addAll(gyeongsangDistricts);
        ranges.addAll(jeonraDistricts);

        return ranges;
    }
}

