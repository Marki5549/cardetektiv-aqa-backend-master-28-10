package de.cardetektiv.app.storages;

import org.jetbrains.annotations.NotNull;

public class ScenarioStorage<T> {

    private final ThreadLocal<T> storage = new ThreadLocal<>();

    public T getLast(){
        return this.storage.get();
    }

    public void save(final @NotNull T content){
        this.storage.set(content);
    }

}
