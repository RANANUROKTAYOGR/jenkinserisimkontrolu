# PowerShell pre-push hook to prevent pushes to 'test' branch
# Usage: git config core.hooksPath .githooks

try {
    $branch = git rev-parse --abbrev-ref HEAD 2>$null
} catch {
    $branch = ''
}

if ($branch -eq 'test') {
    Write-Host "Pushing to 'test' branch is forbidden. Push to 'main' instead." -ForegroundColor Red
    exit 1
}

# Also process stdin for remote updates
$stdin = [Console]::In.ReadToEnd()
if ($stdin) {
    $lines = $stdin -split "\n"
    foreach ($line in $lines) {
        if ([string]::IsNullOrWhiteSpace($line)) { continue }
        $parts = $line -split ' '
        if ($parts.Length -ge 4) {
            $remoteRef = $parts[2]
            $branchName = $remoteRef -replace 'refs/heads/', ''
            if ($branchName -eq 'test') {
                Write-Host "Pushing to 'test' branch is forbidden. Push to 'main' instead." -ForegroundColor Red
                exit 1
            }
        }
    }
}

exit 0

