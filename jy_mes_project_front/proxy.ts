import { NextRequest, NextResponse } from "next/server";

const AUTH_COOKIE = "auth_token";
const PROTECTED_PATHS = ["/middleware-guard"];

export const proxy = (request: NextRequest) => {
  const { pathname } = request.nextUrl;
  const requiresAuth = PROTECTED_PATHS.some((path) =>
    pathname.startsWith(path)
  );

  if (!requiresAuth) {
    return NextResponse.next();
  }

  const token = request.cookies.get(AUTH_COOKIE)?.value;

  if (!token) {
    const loginUrl = new URL("/login", request.url);
    loginUrl.searchParams.set("from", pathname);
    return NextResponse.redirect(loginUrl);
  }

  return NextResponse.next();
};

export const config = {
  matcher: ["/middleware-guard/:path*"],
};