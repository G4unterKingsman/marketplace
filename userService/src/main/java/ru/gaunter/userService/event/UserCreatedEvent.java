package ru.gaunter.userService.event;

import ru.gaunter.userService.entity.Role;

import java.util.Set;
import java.util.UUID;

public class UserCreatedEvent {
        private UUID uuid;
        private String username;
        private Set<Role> role;

}
