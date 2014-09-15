
--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'UPDATER_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('DATAVIZ_MANAGEMENT_STAT','dataviz.adminFeature.ManageStat.name',1,'jsp/admin/plugins/dataviz/ManageStats.jsp','dataviz.adminFeature.ManageStat.description',0,'dataviz',NULL,NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'DATAVIZ_MANAGEMENT_STAT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('DATAVIZ_MANAGEMENT_STAT',1);

