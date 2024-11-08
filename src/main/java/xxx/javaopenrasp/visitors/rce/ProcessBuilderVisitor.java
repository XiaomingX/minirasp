package xxx.javaopenrasp.visitors.rce;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 
 */
public class ProcessBuilderVisitor extends ClassVisitor {

    public String className;

    public ProcessBuilderVisitor(ClassVisitor cv, String className) {
        super(Opcodes.ASM9, cv);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if ("start".equals(name) && "()Ljava/lang/Process;".equals(desc)) {
            mv = new ProcessBuilderVisitorAdapter(mv, access, name, desc);
        }
        return mv;
    }

}
