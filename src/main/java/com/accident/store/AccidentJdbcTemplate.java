package com.accident.store;

import com.accident.model.Accident;
import com.accident.model.AccidentType;
import com.accident.model.Rule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    private final RowMapper<Accident> accidentMapper = (rs, row) -> {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setType(
                AccidentType.of(
                        rs.getInt("type_id"),
                        rs.getString("type_name")
                )
        );
        return accident;
    };

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident acc) {
        // SAVING ACCIDENT
        jdbc.update("INSERT INTO accident (name, text, address, type_id) VALUES (?, ?, ?, ?)",
                acc.getName(), acc.getText(), acc.getAddress(), acc.getTypeId());

        // SAVING A SET OF ACCIDENT'S RULES
        for (Rule rule : acc.getRules()) {
            jdbc.update("INSERT INTO accident_rules (accident_id, rule_id) VALUES (?, ?)",
                acc.getId(), rule.getId());
        }
        return acc;
    }

    public List<Accident> getAccidents() {
        List<Accident> rsl = jdbc.query(
                "SELECT ac.id AS id, ac.name AS name, ac.text AS text, ac.address AS address, " +
                    "ac.type_id AS type_id, at.name AS type_name " +
                    "FROM accident AS ac " +
                    "LEFT JOIN accident_type AS at " +
                    "ON ac.type_id = at.id ",
                    accidentMapper);
        for (Accident acc : rsl) {
            acc.setRules(new HashSet<>(getAccidentRules(acc)));
        }
        return rsl;
    }

    public List<Rule> getAccidentRules(Accident accident) {
        return jdbc.query(
                "SELECT ar.rule_id, r.name " +
                    "FROM accident_rules AS ar " +
                    "INNER JOIN rule AS r " +
                    "ON ar.rule_id = r.id AND ar.accident_id = ?",
                    (rs, row) -> {
                        return Rule.of(rs.getInt("id"), rs.getString("name"));
                    },
                    accident.getId()
        );
    }

    public Accident getAccidentById(int id) {
        Accident rsl = jdbc.queryForObject(
                "SELECT ac.id AS id, ac.name AS name, ac.text AS text, ac.address AS address, " +
                    "ac.type_id AS type_id, at.name AS type_name " +
                    "FROM accident AS ac " +
                    "LEFT JOIN accident_type AS at " +
                    "ON ac.type_id = at.id " +
                    "WHERE ac.id = ?",
                    accidentMapper,
                    id);
        rsl.setRules(new HashSet<>(getAccidentRules(rsl)));
        return rsl;
    }

    public List<Rule> getRules() {
        return jdbc.query(
                "SELECT id, name FROM rule",
                (rs, row) -> {
                    return Rule.of(rs.getInt("id"), rs.getString("name"));
                }
        );
    }

    public Rule getRuleById(int id) {
        return jdbc.queryForObject(
                "SELECT id, name FROM rule WHERE id = ?",
                (rs, row) -> {
                    return Rule.of(rs.getInt("id"), rs.getString("name"));
                },
                id
        );
    }

    public List<AccidentType> getAccidentTypes() {
        return jdbc.query(
                "SELECT id, name FROM accident_type",
                (rs, row) -> {
                    return AccidentType.of(rs.getInt("id"), rs.getString("name"));
                }
        );
    }

    public AccidentType getAccidentTypeById(int id) {
        return jdbc.queryForObject(
                "SELECT id, name FROM accident_type WHERE id = ?",
                (rs, row) -> {
                    return AccidentType.of(rs.getInt("id"), rs.getString("name"));
                },
                id
        );
    }
}
