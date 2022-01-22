package com.game.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Component
@Scope("prototype")
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    @Enumerated(EnumType.STRING)
    private Race race;
    @Enumerated(EnumType.STRING)
    private Profession profession;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;
    private Date birthday;  // registration date ????
    private Boolean banned;

    private static long startTime = 946684800000l; // 01.01.2000 00:00
    private static long endTime = 32503680000000l; // 01.01.3000 00:00

    public Player() {

    }

//    public Player(String name, String title, Race race, Profession profession, Date birthday, Integer experience) {
//        setName(name);
//        setTitle(title);
//        setRace(race);
//        setProfession(profession);
//        setBirthDay(birthday);
//        setExperience(experience);
//        level = countLevel(experience);
//        untilNextLevel = countUnitNextLevel(level, experience);
//        System.out.println("constructor");
//    }
//
//    public Player(String name, String title, Race race, Profession profession, Date birthday, Boolean banned, Integer experience) {
//        this(name, title, race, profession, birthday, experience);
//        setBanned(banned);
//    }

    public static Integer countLevel(Integer experience) {
        return (int) ((Math.sqrt(25 + 2 * experience) - 5) / 10);
    }

    public static Integer countUnitNextLevel(Integer level, Integer experience) {
        return 50 * (level + 1) * (level + 2) - experience;
    }

    public Player update(Player other) {
        if (name == null) {
            name = other.name;
        }

        if (title == null) {
            title = other.title;
        }

        if (race == null) {
            race = other.race;
        }

        if (profession == null) {
            profession = other.profession;
        }

        if (experience == null) {
            experience = other.experience;
        }

        if (birthday == null) {
            birthday = other.birthday;
        }

        if (banned == null) {
            banned = other.banned;
        }

        updateExperience();
        return this;
    }

    public void updateExperience() {
        if (experience != null) {
            level = countLevel(experience);
            untilNextLevel = countUnitNextLevel(level, experience);

        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name.length() < 13) {
            this.name = name;
        }
    }

    public void setTitle(String title) {
        if (title.length() < 31) {
            this.title = title;
        }
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setExperience(Integer experience) {
        if (experience > 0 && experience < 10000001) {
            this.experience = experience;
        }
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public void setBirthDay(Date birthday) {
        long time = birthday.getTime();
        if (time >= startTime && time <= endTime) {
            this.birthday = birthday;
        }
        //System.out.println("setDate " + birthday);
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public boolean isCanCreate() {
        if (name != null && !name.equals("") && title != null && !title.equals("") &&
                race != null && profession != null && birthday != null && experience != null) {
            if (name.length() <= 12 && title.length() <= 30) {
                if (experience >= 0 && experience <= 10_000_000) {
                    if (birthday.getTime() >= startTime && birthday.getTime() <= endTime) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isValidExperience() {
        if (experience != null) {
            if (experience > 0 && experience <= 10_000_000) {
                return true;
            }
            return false;
        }
        return true;
    }

    public boolean isValidBirthDay() {
        if (birthday != null) {
            long time = birthday.getTime();
            if (time >= startTime && time <= endTime) {
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name) &&
                Objects.equals(title, player.title) &&
                race == player.race &&
                profession == player.profession &&
                Objects.equals(experience, player.experience) &&
                Objects.equals(level, player.level) &&
                Objects.equals(untilNextLevel, player.untilNextLevel) &&
                Objects.equals(birthday, player.birthday) &&
                Objects.equals(banned, player.banned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, race, profession, experience, level, untilNextLevel, birthday, banned);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                ", birthDay=" + birthday +
                ", banned=" + banned +
                '}';
    }
}
