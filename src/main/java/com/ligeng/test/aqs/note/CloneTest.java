package com.ligeng.test.aqs.note;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by dev on 16-7-28.
 */
public class CloneTest {
    protected Object clone() throws CloneNotSupportedException {
        Object cloneObj = null;
        try {
            // 克隆对象
            cloneObj = super.clone();

            // 该类的所有属性，包括静态属性
            Field[] filedArr = this.getClass().getDeclaredFields();
            Field field;//属性
            Class fieldType;//属性类型
            Object filedVal;//属性值
            for (int i = 0; i < filedArr.length; i++) {
                field = filedArr[i];
                fieldType = field.getType();
                field.setAccessible(true);
                filedVal = field.get(this);
                /*
                下面代码运行的结果可以表明super.clone()只是浅复制，它只是将原始对象
                的域成员内存地址对拷到了克隆对象中，所以如果是引用类型则指向同一对象，
                若是基本类型，则直接将存储的值复制到克隆对象中，基本类型域成员不需要
                再次单独复制处理。然而，引用类型却是线复制，所以我们需要对引用型单独
                做特殊的复制处理，即深层克隆。

                下面是某次的输出结果，从输出结果可以证实上面的结论：
                i : -1 - -1
                ca : CloneA@480457 - CloneA@480457
                ca1 : CloneA@47858e - CloneA@47858e
                ca2 : CloneA@19134f4 - CloneA@19134f4
                cb : CloneB@df6ccd - CloneB@df6ccd
                sb :  -
                intArr : [[[I@601bb1 - [[[I@601bb1
                caArr : [[[LCloneA;@1ea2dfe - [[[LCloneA;@1ea2dfe
                cbArr : [[[LCloneB;@17182c1 - [[[LCloneB;@17182c1
                int1Arr : [I@13f5d07 - [I@13f5d07
                ca1Arr : [LCloneA;@f4a24a - [LCloneA;@f4a24a
                cb1Arr : [LCloneB;@cac268 - [LCloneB;@cac268
                */
                //Field clFiled = cloneObj.getClass().getDeclaredField(
                //      field.getName());
                //clFiled.setAccessible(true);
                //System.out.println(field.getName() + " : " + filedVal + " - "
                //      + clFiled.get(cloneObj));
                /*
                 * 如果是静态的成员，则不需要深层克隆，因为静态成员属于类成员，
                 * 对所有实例都共享，不要改变现有静态成员的引用指向。
                 *
                 * 如果是final类型变量，则不能深层克隆，即使复制一份后也不能将
                 * 它赋值给final类型变量，这也正是final的限制。否则在使用反射
                 * 赋值给final变量时会抛异常。所以在我们定义一个引用类型是否是
                 * final时，我们要考虑它是否是真真不需要修改它的指向与指向内 容。
                 */
                if (Modifier.isStatic(field.getModifiers())
                    || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }

                //如果是数组
                if (fieldType.isArray()) {
                    /*
                     * 克隆数组，但只是克隆第一维，比如是三维，克隆的结果就相当于
                     * new Array[3][][]， 即只初始化第一维，第二与第三维 还需进一步
                     * 初始化。如果某个Class对象是数 组类对象，则 class.getComponen
                     * tType返回的是复合类型，即元素的类 型，如 果数组是多维的，那么
                     * 它返回的也是数组类型，类型 比class少一维而已，比如 有
                     * Array[][][] arr = new Array[3][][]，则arr.getClass().getC
                     * omponentType返回的为二维Array类型的数组，而且我们可以以这个
                     * 返回的类型来动态创建 三维数组
                     */
                    Object cloneArr = Array.newInstance(filedVal.getClass()
                        .getComponentType(), Array.getLength(filedVal));

                    cloneArr(filedVal, cloneArr);

                    // 设置到克隆对象中
                    filedArr[i].set(cloneObj, cloneArr);
                } else {// 如果不是数组
                    /*
                     * 如果为基本类型或没有实现Cloneable的引用类型时，我们不需要对
                     * 它们做任何克隆处理，因为上面的super.clone()已经对它们进行了
                     * 简单的值拷贝工作了，即已将基本类型的值或引用的地址拷贝到克隆
                     * 对象中去了。super.clone()对基本类型还是属于深克隆，而对引用
                     * 则属于浅克隆。
                     *
                     * String、 Integer...之类为不可变的类，它们都没有实现Cloneable，
                     * 所以对它们进行浅克隆是没有问题的，即它们指向同一不可变对象是没
                     * 有问题。对不可变对象进行克隆是没有意义的。但要 注意，如果是自己
                     * 设计的类，就要考虑是否实现Cloneable与重 写clone方法，如果没有
                     * 这样作，也会进行浅克隆。
                     *
                     * 下面只需对实现了Cloneable的引用进行深度克隆。
                     */

                    // 如果属性对象实现了Cloneable
                    if (filedVal instanceof Cloneable) {

                        // 反射查找clone方法
                        Method cloneMethod;
                        try {
                            cloneMethod = filedVal.getClass().getDeclaredMethod("clone",
                                new Class[] {});

                        } catch (NoSuchMethodException e) {
                            cloneMethod = filedVal.getClass().getMethod("clone",
                                new Class[] {});
                        }
                        //调用克隆方法并设置到克隆对象中
                        filedArr[i].set(cloneObj, cloneMethod.invoke(filedVal,
                            new Object[0]));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cloneObj;
    }


    static private void cloneArr(Object objArr, Object cloneArr) throws Exception {
        Object objTmp;
        Object val = null;
        for (int i = 0; i < Array.getLength(objArr); i++) {
            //注，如果是非数组的基本类型，则返回的是包装类型
            objTmp = Array.get(objArr, i);

            if (objTmp == null) {
                val = null;
            } else if (objTmp.getClass().isArray()) {//如果是数组

                val = Array.newInstance(objTmp.getClass().getComponentType(), Array
                    .getLength(objTmp));
                //如果元素是数组，则递归调用
                cloneArr(objTmp, val);
            } else {//否则非数组

                /*
                 * 如果为基本类型或者是非Cloneable类型的引用类型，则直接对拷值 或
                 * 者是对象的地址。没有实现Cloneable的引用类型会实行浅复制， 这对
                 * 于像String不可变类来说是没有关系的，因为它们可以多实例或 多线程
                 * 共享，但如果即没有实现Cloneable，又是可变以的类，浅复制 则会带来
                 * 危险，因为这些类实例不能共享 ，一个实例里的改变会影响到 另一个实
                 * 例。所以在使用克隆方案的时候一定要考虑可变对象的可克隆性，即需要
                 * 实现Cloneable。
                 *
                 * 注，这里不能使用 objTmp.getClass.isPrimitive()来判断是元素是
                 * 否是基本类型，因为objTmp是通过Array.get获得的，而Array.get返
                 * 回的是Object 类型，也就是说如果是基本类型会自动转换成对应的包
                 * 装类型后返回，所以 我们只能采用原始的类型来判断才行。
                 */
                if (objArr.getClass().getComponentType().isPrimitive()
                    || !(objTmp instanceof Cloneable)) {//基本类型或非Cloneable引用类型
                    val = objTmp;
                } else if (objTmp instanceof Cloneable) {//引用类型，并实现了Cloneable
                    /*
                     *  用反射查找colone方法，注，先使用getDeclaredMethod获取自
                     *  己类 中所定义的方法（包括该类所声明的公共、保护、默认访问
                     *  及私有的 方法），如果没有的话，再使用getMethod，getMethod
                     *  只能获取公有的方法，但还包括了从父类继承过来的公有方法
                     */
                    Method cloneMethod;
                    try {
                        //先获取自己定义的clone方法
                        cloneMethod = objTmp.getClass().getDeclaredMethod("clone",
                            new Class[] {});

                    } catch (NoSuchMethodException e) {
                        //如果自身未定义clone方法，则从父类中找，但父类的clone一定要是public
                        cloneMethod = objTmp.getClass()
                            .getMethod("clone", new Class[] {});
                    }
                    cloneMethod.setAccessible(true);
                    val = cloneMethod.invoke(objTmp, new Object[0]);

                }
            }
            // 设置克隆数组元素值
            Array.set(cloneArr, i, val);
        }
    }

}
