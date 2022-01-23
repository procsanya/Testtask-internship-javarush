package com.game.entity;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class Specifications {
    public static Specification<Player> filterByName(String name) {
        return (root, query, cb) -> name == null ? null : cb.like(root.get("name"), "%"+name+"%");
    }

    public static Specification<Player> filterByTitle(String title) {
        return (root, query, cb) -> title == null ? null : cb.like(root.get("title"), "%"+title+"%");
    }

    public static Specification<Player> filterByRace(Race race) {
        return (root, query, cb) -> race == null ? null : cb.equal(root.get("race"), race);
    }

    public static Specification<Player> filterByProfession(Profession profession) {
        return (root, query, cb) -> profession == null ? null : cb.equal(root.get("profession"), profession);
    }

    public static Specification<Player> filterByAfter(Long after) {
        return (root, query, cb) -> after == null ? null : cb.greaterThanOrEqualTo(root.get("birthday"), new Date(after));
    }

    public static Specification<Player> filterByBefore(Long before) {
        return (root, query, cb) -> before == null ? null : cb.lessThanOrEqualTo(root.get("birthday"), new Date(before));
    }

    public static Specification<Player> filterByBanned(Boolean isBanned) {
        return (root, query, cb) -> {
            if (isBanned == null) {
                return null;
            }
            if (isBanned) {
                return cb.isTrue(root.get("banned"));
            }
            return cb.isFalse(root.get("banned"));
        };
    }

    public static Specification<Player> filterByMinExperience(Integer minExperience) {
        return (root, query, cb) -> minExperience == null ? null : cb.greaterThanOrEqualTo(root.get("experience"), minExperience);
    }

    public static Specification<Player> filtersByMaxExperience(Integer maxExperience) {
        return (root, query, cb) -> maxExperience == null ? null : cb.lessThanOrEqualTo(root.get("experience"), maxExperience);
    }

    public static Specification<Player> filterByMinLevel(Integer minLevel) {
        return (root, query, cb) -> minLevel == null ? null : cb.greaterThanOrEqualTo(root.get("level"), minLevel);
    }

    public static Specification<Player> filterByMaxLevel(Integer maxLevel) {
        return (root, query, cb) -> maxLevel == null ? null : cb.lessThanOrEqualTo(root.get("level"), maxLevel);
    }
}
