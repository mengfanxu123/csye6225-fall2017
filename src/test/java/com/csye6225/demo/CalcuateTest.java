package com.csye6225.demo;


import org.junit.Assert;
import org.junit.Test;
    public class CalcuateTest {
        Calcuate calcuate;

        @Test
        public void testAdd() {
            calcuate = new Calcuate();
            int result = calcuate.add(2, 3);
            Assert.assertEquals("加法有问题", 5, result);
        /*
         * "加法有问题"：期望值和实际值不一致时，显示的信息
         * 5 ：期望值
         * result ：实际值
         */
        }

        @Test
        public void testSubtract() {
            calcuate = new Calcuate();
            int result = calcuate.subtract(12, 2);
            Assert.assertEquals("减法有问题", 10000, result); //故意设置减法期望值为10000
        }

        @Test
        public void testMultiply() {
            calcuate = new Calcuate();
            int result = calcuate.multiply(2, 3);
            Assert.assertEquals("乘法有问题", 6, result);
        }

        @Test
        public void testDivide() {
            calcuate = new Calcuate();
            int result = calcuate.divide(6, 3);
            Assert.assertEquals("除法有问题", 2, result);
        }
    }


