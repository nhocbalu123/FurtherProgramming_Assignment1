package org.claimsystem.g24fp.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TriggerHandle {
    static final String TRIGGER_SQL = "CREATE OR REPLACE FUNCTION log_trigger()" +
            "RETURNS TRIGGER AS" +
            "$$" +
            "Declare action_by varchar(20);" +
            "BEGIN" +
            "    action_by = ?;" +
            "    IF TG_OP = 'DELETE' THEN" +
            "        INSERT INTO log (user_name, action_type, alter_table, entity_id, old_value, new_value, is_active)" +
            "        VALUES (action_by, TG_OP, TG_TABLE_NAME, OLD.id, row_to_json(OLD), null, false);" +
            "    ELSIF TG_OP = 'UPDATE' THEN" +
            "        INSERT INTO log (user_name, action_type, alter_table, entity_id, old_value, new_value)" +
            "        VALUES (action_by, TG_OP, TG_TABLE_NAME, OLD.id, row_to_json(OLD), row_to_json(NEW));" +
            "    ELSIF TG_OP = 'INSERT' THEN" +
            "        INSERT INTO log (user_name, action_type, alter_table, entity_id, old_value, new_value)" +
            "        VALUES (action_by, TG_OP, TG_TABLE_NAME, NEW.id, null, row_to_json(NEW));" +
            "    END IF;" +
            "    RETURN NEW;" +
            "END;\n$$ LANGUAGE plpgsql;";

    static final String TABLE_TRIGGER = "CREATE TRIGGER log_trigger" +
            "AFTER INSERT OR UPDATE OR DELETE ON ?" +
            "FOR EACH ROW EXECUTE FUNCTION log_trigger();";

    public static void executeTrigger(Connection conn, String tableName, String actionBy) {
        try {
            PreparedStatement ps = conn.prepareStatement(TRIGGER_SQL);
            ps.setString(1, actionBy);
            ps.executeUpdate();
            ps = conn.prepareStatement(TABLE_TRIGGER);
            ps.setString(1, tableName);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
