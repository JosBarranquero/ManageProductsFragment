package com.barranquero.manageproductsrecycler.model;

/**
 * * g o a t s e x * g o a t s e x * g o a t s e x *
 * g                                               g
 * o /     \             \            /    \       o
 * a|       |             \          |      |      a
 * t|       `.             |         |       :     t
 * s`        |             |        \|       |     s
 * e \       | /       /  \\\   --__ \\       :    e
 * x  \      \/   _--~~          ~--__| \     |    x
 * *   \      \_-~                    ~-_\    |    *
 * g    \_     \        _.--------.______\|   |    g
 * o      \     \______// _ ___ _ (_(__>  \   |    o
 * a       \   .  C ___)  ______ (_(____>  |  /    a
 * t       /\ |   C ____)/      \ (_____>  |_/     t
 * s      / /\|   C_____)       |  (___>   /  \    s
 * e     |   (   _C_____)\______/  // _/ /     \   e
 * x     |    \  |__   \\_________// (__/       |  x
 * *    | \    \____)   `----   --'             |  *
 * g    |  \_          ___\       /_          _/ | g
 * o   |              /    |     |  \            | o
 * a   |             |    /       \  \           | a
 * t   |          / /    |         |  \           |t
 * s   |         / /      \__/\___/    |          |s
 * e  |           /        |    |       |         |e
 * x  |          |         |    |       |         |x
 * * g o a t s e x * g o a t s e x * g o a t s e x *
 */

public class Pharmacy {
    private int id;
    private String cif;
    private String address;
    private String phone;
    private String email;

    public Pharmacy() {
    }

    public Pharmacy(String address, String cif, String email, int id, String phone) {
        this.address = address;
        this.cif = cif;
        this.email = email;
        this.id = id;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
