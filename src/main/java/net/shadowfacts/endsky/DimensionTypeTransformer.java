package net.shadowfacts.endsky;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

/**
 * @author shadowfacts
 */
public class DimensionTypeTransformer implements IClassTransformer, Opcodes {

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		boolean obfuscated = !name.equals(transformedName);
		if ("net.minecraft.world.DimensionType".equals(transformedName)) {

			ClassNode node = new ClassNode();
			ClassReader reader = new ClassReader(bytes);
			reader.accept(node, 0);

			transform(node, obfuscated);

			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			node.accept(writer);
			return writer.toByteArray();

		}
		return bytes;
	}

	private void transform(ClassNode classNode, boolean obfuscated) {
		final String CREATE_DIMENSION = obfuscated ? "func_186070_d" : "createDimension";
		final String HOOK_DESC = "(Lnet/minecraft/world/WorldProvider;)Lnet/minecraft/world/WorldProvider;";

		for (MethodNode method : classNode.methods) {

			if (CREATE_DIMENSION.equals(method.name)) {
				for (AbstractInsnNode instruction : method.instructions.toArray()) {
					if (instruction.getOpcode() == INVOKEVIRTUAL) {

						MethodInsnNode methodInsn = (MethodInsnNode)instruction;

						if ("java/lang/reflect/Constructor".equals(methodInsn.owner) &&
								"newInstance".equals(methodInsn.name) &&
								"([Ljava/lang/Object;)Ljava/lang/Object;".equals(methodInsn.desc)) {

							if (methodInsn.getNext().getOpcode() == CHECKCAST) {
								System.out.println("Found insertion point, patching net.minecraft.world.DimensionType.createDimension");

								method.instructions.insert(methodInsn.getNext(), new MethodInsnNode(INVOKESTATIC, "net/shadowfacts/endsky/ESHooks", "createWorldProvider", HOOK_DESC, false));

							} else {
								System.err.println("Couldn't find insertion point for net.minecraft.world.DimensionType.createDimension");
							}

						}

					}
				}
			}

		}

	}

}
