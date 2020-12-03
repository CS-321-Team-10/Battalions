/*
 * School:         University of Alabama in Huntsville
 * Course Title:   Object-Oriented Programming in Java
 * Instructor:     Dr. Dan Rochowiak
 *
 * Course Number:  CS 321
 * Course Section: 01
 * Term:           Fall 2020
 *
 * Team:           10
 * Team Members:   Scott Clarke
 *                 Guess Crow
 *                 Blocker Griffin
 *                 Thomas Lemmons
 *                 Bryant Terry
 */
package battalions.data;

/**
 * Contains information about the type of a tile and its effectFlags on a unit.
 * @author Scott
 */
public enum TileType
{
    /**
     * A dark field tile variant with no effect.
     */
    FieldDark(TileEffectFlags.NONE),

    /**
     * A light field tile variant with no effect.
     */
    FieldLight(TileEffectFlags.NONE),

    /**
     * A dark grass tile variant with no effect.
     */
    GrassDark(TileEffectFlags.NONE),

    /**
     * A light grass tile variant with no effect.
     */
    GrassLight(TileEffectFlags.NONE),

    /**
     * A horizontal bridge tile variant with no effect.
     */
    BridgeHorizontal(TileEffectFlags.NONE),

    /**
     * A vertical bridge tile variant with no effect.
     */
    BridgeVertical(TileEffectFlags.NONE),

    /**
     * A tree tile variant that boosts a unit's chance of dodging an attack.
     */
    Forest(TileEffectFlags.BOOSTS_DODGE),

    /**
     * A sand tile variant that has no effect.
     */
    Sand(TileEffectFlags.NONE),

    /**
     * A northeast corner sand tile variant that has no effect.
     */
    SandNorthEast(TileEffectFlags.NONE),

    /**
     * A northwest corner sand tile variant that has no effect.
     */
    SandNorthWest(TileEffectFlags.NONE),

    /**
     * A southeast corner sand tile variant that has no effect.
     */
    SandSouthEast(TileEffectFlags.NONE),

    /**
     * A southwest corner sand tile variant that has no effect.
     */
    SandSouthWest(TileEffectFlags.NONE),

    /**
     * An inward northeast corner sand tile variant that has no effect.
     */
    SandInwardNorthEast(TileEffectFlags.NONE),

    /**
     * An inward northwest corner sand tile variant that has no effect.
     */
    SandInwardNorthWest(TileEffectFlags.NONE),

    /**
     * An inward southeast corner sand tile variant that has no effect.
     */
    SandInwardSouthEast(TileEffectFlags.NONE),

    /**
     * An inward southwest corner sand tile variant that has no effect.
     */
    SandInwardSouthWest(TileEffectFlags.NONE),

    /**
     * A horizontal wall tile variant that blocks unit movement.
     */
    WallHorizontal(TileEffectFlags.IMPASSABLE),

    /**
     * A vertical wall tile variant that blocks unit movement.
     */
    WallVertical(TileEffectFlags.IMPASSABLE),

    /**
     * A northeast corner wall tile variant that blocks unit movement.
     */
    WallNorthEast(TileEffectFlags.IMPASSABLE),

    /**
     * A northwest corner wall tile variant that blocks unit movement.
     */
    WallNorthWest(TileEffectFlags.IMPASSABLE),

    /**
     * A southeast corner wall tile variant that blocks unit movement.
     */
    WallSouthEast(TileEffectFlags.IMPASSABLE),

    /**
     * A southwest corner wall tile variant that blocks unit movement.
     */
    WallSouthWest(TileEffectFlags.IMPASSABLE),

    /**
     * An eastward 3-way junction wall tile variant that blocks unit movement.
     */
    Wall3WayEast(TileEffectFlags.IMPASSABLE),

    /**
     * A northward 3-way junction wall tile variant that blocks unit movement.
     */
    Wall3WayNorth(TileEffectFlags.IMPASSABLE),

    /**
     * A southward 3-way junction wall tile variant that blocks unit movement.
     */
    Wall3WaySouth(TileEffectFlags.IMPASSABLE),

    /**
     * A westward 3-way junction wall tile variant that blocks unit movement.
     */
    Wall3WayWest(TileEffectFlags.IMPASSABLE),

    /**
     * A 4-way junction wall tile variant that blocks unit movement.
     */
    Wall4Way(TileEffectFlags.IMPASSABLE),

    /**
     * A horizontal water tile variant that blocks unit movement.
     */
    WaterHorizontal(TileEffectFlags.IMPASSABLE),

    /**
     * A vertical water tile variant that blocks unit movement.
     */
    WaterVertical(TileEffectFlags.IMPASSABLE),

    /**
     * A northeast corner water tile variant that blocks unit movement.
     */
    WaterNorthEast(TileEffectFlags.IMPASSABLE),

    /**
     * A northwest corner water tile variant that blocks unit movement.
     */
    WaterNorthWest(TileEffectFlags.IMPASSABLE),

    /**
     * A southeast corner water tile variant that blocks unit movement.
     */
    WaterSouthEast(TileEffectFlags.IMPASSABLE),

    /**
     * A southwest corner water tile variant that blocks unit movement.
     */
    WaterSouthWest(TileEffectFlags.IMPASSABLE),

    /**
     * An eastward 3-way junction water tile variant that blocks unit movement.
     */
    Water3WayEast(TileEffectFlags.IMPASSABLE),

    /**
     * A northward 3-way junction water tile variant that blocks unit movement.
     */
    Water3WayNorth(TileEffectFlags.IMPASSABLE),

    /**
     * A southward 3-way junction water tile variant that blocks unit movement.
     */
    Water3WaySouth(TileEffectFlags.IMPASSABLE),

    /**
     * A westward 3-way junction water tile variant that blocks unit movement.
     */
    Water3WayWest(TileEffectFlags.IMPASSABLE),

    /**
     * A 4-way junction water tile variant that blocks unit movement.
     */
    Water4Way(TileEffectFlags.IMPASSABLE),

    /**
     * A broken building tile variant that blocks unit movement.
     */
    BrokenBuilding(TileEffectFlags.IMPASSABLE),

    /**
     * A broken tower tile variant that blocks unit movement.
     */
    BrokenTower(TileEffectFlags.IMPASSABLE);

    /**
     * Bit-flags representing the effects this tile has on a unit.
     */
    public final int effectFlags;

    /**
     * Initializes a new value of the TileType enumeration.
     * @param effectFlags bit-flags representing the effects this tile has on a unit
     */
    private TileType(int effectFlags)
    {
        this.effectFlags = effectFlags;
    }

    /**
     * Returns whether or not this tile type is impassable.
     * @return true, if this tile type is impassable; false, otherwise
     */
    public boolean isImpassable()
    {
        return TileEffectFlags.includes(effectFlags, TileEffectFlags.IMPASSABLE);
    }

    /**
     * Returns whether or not this tile type boosts a unit's chance of dodging an attack.
     * @return true, if this tile type boosts dodge chance; false, otherwise
     */
    public boolean boostsDodge()
    {
        return TileEffectFlags.includes(effectFlags, TileEffectFlags.BOOSTS_DODGE);
    }

    /**
     * Returns whether or not this tile type reduces a unit's movement range.
     * @return true, if this tile type reduces movement range; false, otherwise
     */
    public boolean reducesMovement()
    {
        return TileEffectFlags.includes(effectFlags, TileEffectFlags.REDUCES_MOVEMENT);
    }
}