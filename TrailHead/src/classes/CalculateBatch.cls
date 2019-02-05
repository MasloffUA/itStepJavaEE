global class CalculateBatch implements Database.Batchable<sObject> {
    
    
	global Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator('SELECT ID FROM Opportunity');
    }
    global void execute(Database.BatchableContext bc, List<Opportunity> scope){
        List<ID> oppsId = new List<ID>();
        for (Opportunity o : scope) {
			oppsId.add(o.ID);            
        }
        CalculateSum.doCalc(oppsId, null);
    }    
    global void finish(Database.BatchableContext bc){
        System.debug('Done');
    } 
}