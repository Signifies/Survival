### Need to implement permission checking similar to the eKernel system. Reference permission handling as needed.
###This is very simple working version of things.
##Fixes:

## TODO:
- Whitelist notifications.
- Administrative function notifcations.
- Administrator chat.
- Some command/chat features.
- Optimised code.

### Code idea for default and reverse permissionality.
` if( useRankSystem() && (User.isPermissible(player, Rank.<RANK>) || SPermissions.PERMISSION_checkPermission(player))) {
        
        //Do things they are allowed to do

  }else {
  
    sender.sendMessage(defaultMessage(instance.permissionDefault(), instance.getMessage()));
    
  }`