package com.scholastic.sbam.server.fastSearch;

import com.scholastic.sbam.shared.objects.CacheStatusInstance;

/**
 * This version of the institution cache is restricted to customers with agreements (active or not).
 * 
 * It is updateable, so that new agreements (or customer changes) can be reflected in the cache.
 * 
 * Note that customers are not interactively removed from the cache, only added.  The search servlet can take care of filtering out customers without current, active agreements.
 * 
 * @author Bob Lacatena
 *
 */
public class SiteInstitutionCache extends InstitutionCache {
	
	protected static SiteInstitutionCache singleton = null;
	
	protected static final String SITE_UCN_CLAUSE = "select distinct site_ucn from agreement_site where agreement_site.site_loc_code <> '' and agreement_site.status <> 'X' union select distinct ucn from site where site.status <> 'X'";
	protected static final String SITE_SQL_FROM = "institution WHERE institution.ucn in (" + SITE_UCN_CLAUSE + ") ";
	
	/**
	 * SQL statement with a join to agreement
	 */
	protected static final String SITE_SQL = "SELECT DISTINCT ucn, parent_ucn, institution_name, address1, address2, address3, city, state, zip, country, phone, fax, alternate_ids FROM " + SITE_SQL_FROM;
	
//	public SiteInstitutionCache() {
//		config = new InstitutionCacheConfig();
//		config.setUpdateable(true);
//		init();
//	}
	
	public SiteInstitutionCache(InstitutionCacheConfig config) {
		if (config == null)
			config = new InstitutionCacheConfig();
		config.setUpdateable(true);
		this.config = config;
		init();
	}
	
	@Override
	public String getCacheName() {
		return "Site Institution Cache";
	}
	
	@Override
	protected String getBaseSqlStatement() {
		return SITE_SQL;
	}
	
	public static synchronized SiteInstitutionCache getSingleton(InstitutionCacheConfig config) throws InstitutionCacheConflict {
		if (singleton == null) {
			singleton = new SiteInstitutionCache(config);
		} else {
			if (config != null && singleton.config != config && !configsAreEqual(singleton.config, config)) {
			//	Config has changed, so we need to re-initialize the singleton	
				if (!singleton.init(config)) {
					//	If it wouldn't reinitialize because another init was running, then this particular request failed
					System.out.println("Running   config -- " + singleton.config);
					System.out.println("Requested config -- " + config);
					throw new InstitutionCacheConflict();
				}
			}
		}
		//	In any event, return the singleton we have here
		return singleton;
	}

	
	public static synchronized SiteInstitutionCache getSingleton() throws InstitutionCacheConflict {
		return getSingleton(null);
	}

	
	@Override
	public String getTableName() {
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		sb.append(SITE_UCN_CLAUSE);
		sb.append(") x");
//		appendStatusClause(sb);
		return sb.toString();
	}

	@Override
	public String getName() {
		return "Site Cache";
	}

	@Override
	public int getSeq() {
		return 2;
	}

	@Override
	public String getKey() {
		return CacheStatusInstance.SITE_CACHE_KEY;
	}
}
