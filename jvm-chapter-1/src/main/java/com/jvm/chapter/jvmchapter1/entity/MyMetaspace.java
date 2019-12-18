package com.jvm.chapter.jvmchapter1.entity;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author deke
 * @description
 * @date 2019/12/18
 */
public class MyMetaspace extends ClassLoader {
    public static List<Class<?>> createClasses() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (int i = 0; i < 10000000; ++i) {
            ClassWriter cw = new ClassWriter(0);
            cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
            MethodVisitor mw = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mw.visitVarInsn(Opcodes.ALOAD, 0);
            mw.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            mw.visitInsn(Opcodes.RETURN);
            mw.visitMaxs(1, 1);
            mw.visitEnd();
            Metaspace test = new Metaspace();
            byte[] code = cw.toByteArray();
            Class<?> exampleClass = test.defineClass("Class" + i, code, 0, code.length);
            classes.add(exampleClass);
        }
        return classes;
    }

    private static class Metaspace {
        private String c;
        private byte[] code;
        private int i;
        private int length;


        public Class<?> defineClass(String c,byte[] code,int i,int length){
            Metaspace aClass = new Metaspace();
            aClass.setC(c);
            aClass.setCode(code);
            aClass.setI(i);
            aClass.setLength(length);
            return aClass.getClass();
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public byte[] getCode() {
            return code;
        }

        public void setCode(byte[] code) {
            this.code = code;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}
