package com.celeste.view.event;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;

import java.util.function.Predicate;

public final class EventFilters {

    /**
     * Checks if the entity has metadata.
     *
     * @param <E> Predicate
     * @param key Key for the metadata
     *
     * @return Predicate
     */
    public static <E extends EntityEvent> Predicate<E> hasMetadata(final String key) {
        return e -> e.getEntity().hasMetadata(key);
    }

    /**
     * Checks if player equals to target
     *
     * @param player Player
     * @param <E> Predicate
     * @return Predicate
     */
    public static <E extends PlayerEvent> Predicate<E> equals(final Player player) {
        return event -> event.getPlayer().getUniqueId().equals(player.getUniqueId());
    }

    /**
     * Checks if player has permission
     *
     * @param permission String of the permission
     * @param <E> Predicate
     * @return Predicate
     */
    public static <E extends PlayerEvent> Predicate<E> permission(final String permission) {
        return event -> event.getPlayer().hasPermission(permission);
    }

}