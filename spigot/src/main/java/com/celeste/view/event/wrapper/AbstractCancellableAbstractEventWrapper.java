package com.celeste.view.event.wrapper;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bukkit.event.Cancellable;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractCancellableAbstractEventWrapper extends AbstractEventWrapper implements Cancellable {

    private boolean cancelled;

}