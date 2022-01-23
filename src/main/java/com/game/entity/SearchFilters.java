package com.game.entity;

import com.game.controller.PlayerOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
// This class was created in order to remove 14 arguments in PlayerController.getPlayers()
public class SearchFilters {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long after;
    private Long before;
    private Boolean banned;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer minLevel;
    private Integer maxLevel;
    private PlayerOrder order;
    private Integer pageNumber;
    private Integer pageSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public long getAfter() {
        return after;
    }

    public void setAfter(Long after) {
            this.after = after;
    }

    public Long getBefore() {
        return before;
    }

    public void setBefore(Long before) {
            this.before = before;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public PlayerOrder getOrder() {
        return order;
    }

    public void setOrder(PlayerOrder order) {
        this.order = order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Pageable getPageable() {
        if (order == null) {
            order = PlayerOrder.ID;
        }
        if (pageNumber == null) {
            pageNumber = 0;
        }
        if (pageSize == null) {
            pageSize = 3;
        }

        return PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));
    }

    public Specification<Player> getSpecification() {
        return Specification.where(Specifications.filterByName(name)).and(Specifications.filterByTitle(title))
                    .and(Specifications.filterByRace(race)).and(Specifications.filterByProfession(profession))
                    .and(Specifications.filterByAfter(after)).and(Specifications.filterByBefore(before))
                    .and(Specifications.filterByBanned(banned)).and(Specifications.filterByMinExperience(minExperience))
                    .and(Specifications.filtersByMaxExperience(maxExperience)).and(Specifications.filterByMinLevel(minLevel))
                    .and(Specifications.filterByMaxLevel(maxLevel));
    }

    @Override
    public String toString() {
        return "SearchFilters{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", after=" + after +
                ", before=" + before +
                ", banned=" + banned +
                ", minExperience=" + minExperience +
                ", maxExperience=" + maxExperience +
                ", minLevel=" + minLevel +
                ", maxLevel=" + maxLevel +
                ", order=" + order +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
