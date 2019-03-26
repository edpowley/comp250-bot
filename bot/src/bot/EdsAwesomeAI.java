package bot;

import ai.abstraction.AbstractionLayerAI;
import ai.abstraction.pathfinding.AStarPathFinding;
import ai.core.AI;
import ai.core.ParameterSpecification;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import rts.*;
import rts.units.Unit;
import rts.units.UnitType;
import rts.units.UnitTypeTable;

public class EdsAwesomeAI extends AbstractionLayerAI {    
    private UnitTypeTable utt;
    private UnitType worker;
    
    public EdsAwesomeAI(UnitTypeTable utt) {
        super(new AStarPathFinding());
        this.utt = utt;
        worker = utt.getUnitType("Worker");
    }
    

    @Override
    public void reset() {
    }

    
    @Override
    public AI clone() {
        return new EdsAwesomeAI(utt);
    }
   
    
    @Override
    public PlayerAction getAction(int player, GameState gs) {
        PhysicalGameState pgs = gs.getPhysicalGameState();
        
        for (Unit unit : pgs.getUnits()) {
            if (unit.getPlayer() == player
            		&& unit.getType().canAttack) {
            		Unit enemy = findEnemyUnit(player, pgs);
            		if (enemy != null) {
            			attack(unit, enemy); // asdf
            		}
            }
        }
        
        return translateActions(player, gs);
    }
    
    private Unit findEnemyUnit(int player, PhysicalGameState pgs) {
    		for (Unit unit : pgs.getUnits()) {
    			if (unit.getPlayer() != player && unit.getPlayer() != -1) {
    				return unit;
    			}
    		}
    		return null;
    }
    
    @Override
    public List<ParameterSpecification> getParameters() {
        return new ArrayList<>();
    }
}
