package de.agdsn.jcroft.database.model;

import de.agdsn.jcroft.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "rooms")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false, updatable = false)
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @Size(max = 45)
    @Column(name = "room_number", nullable = false, updatable = true)
    private String roomNumber;

    @Column(name = "floor", nullable = false, updatable = true)
    private int floor;

    //can anyone live in this room?
    @Column(name = "inhabitable", nullable = false, updatable = true)
    private boolean inhabitable = true;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "id")
    private List<PatchPort> patchPorts = new ArrayList<>();

    public Room (Building building, String roomNumber, int floor) {
        Objects.requireNonNull(building);
        StringUtils.requireNonEmptyString(roomNumber, "room number");

        this.building = building;
        this.roomNumber = roomNumber;
        this.floor = floor;
    }

    protected Room () {
        //
    }

    public int getId() {
        return id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isInhabitable() {
        return inhabitable;
    }

    public void setInhabitable(boolean inhabitable) {
        this.inhabitable = inhabitable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isInhabited () {
        return this.user != null;
    }

    public List<PatchPort> listPatchPorts() {
        return patchPorts;
    }

    public void addPatchPort (PatchPort port) {
        this.patchPorts.add(port);
    }

    public void removePatchPort (PatchPort port) {
        this.patchPorts.remove(port);
    }

    public void removeAllPatchPorts () {
        this.patchPorts.clear();
    }

}
