package com.honeymoon.events;

public class DeletedEvent {
  protected boolean entityFound = true;

  public boolean isEntityFound() {
    return entityFound;
  }
}
