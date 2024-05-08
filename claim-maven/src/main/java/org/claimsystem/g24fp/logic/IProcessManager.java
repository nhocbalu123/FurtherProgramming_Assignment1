package org.claimsystem.g24fp.logic;

import org.claimsystem.g24fp.domain.Claim;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface IProcessManager<T> {
    void add(Connection conn, Map<String, String> info);
    void update(Connection conn, Map<String, String> info);
    void delete(Connection conn, String id);
    T getOne(Connection conn, String id);
    List<T> getAll(Connection conn);
}
