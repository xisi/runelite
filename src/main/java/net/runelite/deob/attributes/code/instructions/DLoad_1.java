package net.runelite.deob.attributes.code.instructions;

import net.runelite.deob.attributes.code.Instruction;
import net.runelite.deob.attributes.code.InstructionType;
import net.runelite.deob.attributes.code.Instructions;
import net.runelite.deob.attributes.code.instruction.types.LVTInstruction;
import net.runelite.deob.execution.Frame;
import net.runelite.deob.execution.InstructionContext;
import net.runelite.deob.execution.Stack;
import net.runelite.deob.execution.StackContext;
import net.runelite.deob.execution.Type;
import net.runelite.deob.execution.VariableContext;
import net.runelite.deob.execution.Variables;

import java.io.IOException;

public class DLoad_1 extends Instruction implements LVTInstruction
{
	public DLoad_1(Instructions instructions, InstructionType type, int pc) throws IOException
	{
		super(instructions, type, pc);
	}

	@Override
	public void execute(Frame frame)
	{
		InstructionContext ins = new InstructionContext(this, frame);
		Stack stack = frame.getStack();
		Variables variables = frame.getVariables();
		
		VariableContext vctx = variables.get(1);
		assert vctx.getType().equals(new Type(double.class.getName()));
		ins.read(vctx);
		
		StackContext ctx = new StackContext(ins, vctx.getType());
		stack.push(ctx);
		
		ins.push(ctx);
		
		frame.addInstructionContext(ins);
	}
	
	@Override
	public int getVariableIndex()
	{
		return 1;
	}
	
	@Override
	public Instruction setVariableIndex(int idx)
	{
		return new DLoad(this.getInstructions(), idx);
	}

	@Override
	public boolean store()
	{
		return false;
	}
	
	@Override
	public Instruction makeGeneric()
	{
		return new DLoad(this.getInstructions(), 1);
	}
}
