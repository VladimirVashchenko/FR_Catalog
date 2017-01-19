;This file will be executed next to the application bundle image
;I.e. current directory will contain folder FR_Catalog-1.0 with application files
[Setup]
AppId={{fr_catalog}}
AppName=FR_Catalog-1.0
AppVersion=1.0
AppVerName=FR_Catalog-1.0 1.0
AppPublisher=Private
AppComments=FR_Catalog-1.0
AppCopyright=Copyright (C) 2017
;AppPublisherURL=http://java.com/
;AppSupportURL=http://java.com/
;AppUpdatesURL=http://java.com/
DefaultDirName={pf}\FR_Catalog-1.0
DisableStartupPrompt=Yes
DisableDirPage=No
DisableProgramGroupPage=Yes
DisableReadyPage=No
DisableFinishedPage=No
DisableWelcomePage=Yes
DefaultGroupName=Private
;Optional License
LicenseFile=
;WinXP or above
MinVersion=0,5.1 
OutputBaseFilename=FR_Catalog-1.0
Compression=lzma
SolidCompression=yes
PrivilegesRequired=lowest
SetupIconFile=FR_Catalog-1.0\FR_Catalog-1.0.ico
UninstallDisplayIcon={app}\FR_Catalog-1.0.ico
UninstallDisplayName=FR_Catalog-1.0
WizardImageStretch=No
WizardSmallImageFile=FR_Catalog-1.0-setup-icon.bmp
ArchitecturesInstallIn64BitMode=x64


[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
Source: "FR_Catalog-1.0\FR_Catalog-1.0.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "FR_Catalog-1.0\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\FR_Catalog-1.0"; Filename: "{app}\FR_Catalog-1.0.exe"; IconFilename: "{app}\FR_Catalog-1.0.ico"; Check: returnFalse()
Name: "{commondesktop}\FR_Catalog-1.0"; Filename: "{app}\FR_Catalog-1.0.exe";  IconFilename: "{app}\FR_Catalog-1.0.ico"; Check: returnTrue()


[Run]
Filename: "{app}\FR_Catalog-1.0.exe"; Parameters: "-Xappcds:generatecache"; Check: returnFalse()
Filename: "{app}\FR_Catalog-1.0.exe"; Description: "{cm:LaunchProgram,FR_Catalog-1.0}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\FR_Catalog-1.0.exe"; Parameters: "-install -svcName ""FR_Catalog-1.0"" -svcDesc ""FR_Catalog-1.0"" -mainExe ""FR_Catalog-1.0.exe""  "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\FR_Catalog-1.0.exe "; Parameters: "-uninstall -svcName FR_Catalog-1.0 -stopOnUninstall"; Check: returnFalse()

[Code]
function returnTrue(): Boolean;
begin
  Result := True;
end;

function returnFalse(): Boolean;
begin
  Result := False;
end;

function InitializeSetup(): Boolean;
begin
// Possible future improvements:
//   if version less or same => just launch app
//   if upgrade => check if same app is running and wait for it to exit
//   Add pack200/unpack200 support? 
  Result := True;
end;  
