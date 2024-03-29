package by.it_academy.MDK29522.dao.entity;

import by.it_academy.MDK29522.dao.entity.Artist;
import by.it_academy.MDK29522.dao.entity.Genre;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "app.vote")
public class Vote {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "date_create")
    @CreationTimestamp
    private LocalDate dateCreate;

    @Column(name = "about")
    private String about;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinTable(
            name="cross_artists",
            joinColumns=
            @JoinColumn(name="id_vote", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="id_artist", referencedColumnName="id")
    )
    private Artist artist;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cross_genres",
            joinColumns =
            @JoinColumn(name = "id_vote", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "id_genre", referencedColumnName = "id")
    )
    private List<Genre> genres;

    public Vote() {
    }

    public Vote(Long id, LocalDate dateCreate, String about, String email, Artist artist, List<Genre> genres) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.about = about;
        this.email = email;
        this.artist = artist;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(id, vote.id) && Objects.equals(dateCreate, vote.dateCreate) && Objects.equals(about, vote.about) && Objects.equals(email, vote.email) && Objects.equals(artist, vote.artist) && Objects.equals(genres, vote.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreate, about, email, artist, genres);
    }

    @Override
    public String toString() {
        String genresToString = "";
        for(Genre genre : genres){
            genresToString = genresToString + " " +genre.toString();
        }
        return "Vote{" +
                "id=" + id +
                ", dateCreate=" + dateCreate +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                ", artist=" + artist.toString() +
                ", genres=" + genresToString +
                '}';
    }
}
