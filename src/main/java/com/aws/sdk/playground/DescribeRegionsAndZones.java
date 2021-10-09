package com.aws.sdk.playground;

import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class DescribeRegionsAndZones {

    public static void main(String[] args) {

        Ec2Client ec2 = Ec2Client.create();

        describeEC2RegionsAndZones(ec2);
        ec2.close();
    }

    public static void describeEC2RegionsAndZones( Ec2Client ec2) {
        try {

            final DescribeSecurityGroupsResponse describeSecurityGroupsResponse = ec2.describeSecurityGroups();
            System.out.println(describeSecurityGroupsResponse);

            DescribeRegionsResponse regionsResponse = ec2.describeRegions();

            for(Region region : regionsResponse.regions()) {
                System.out.printf(
                        "Found Region %s " +
                                "with endpoint %s",
                        region.regionName(),
                        region.endpoint());
                System.out.println();
            }

            DescribeAvailabilityZonesResponse zonesResponse =
                    ec2.describeAvailabilityZones();

            for(AvailabilityZone zone : zonesResponse.availabilityZones()) {
                System.out.printf(
                        "Found Availability Zone %s " +
                                "with status %s " +
                                "in region %s",
                        zone.zoneName(),
                        zone.state(),
                        zone.regionName());
                System.out.println();
            }

            final DescribeAccountAttributesResponse describeAccountAttributesResponse = ec2.describeAccountAttributes();
            System.out.println(describeAccountAttributesResponse);
//            System.out.println(ec2.describeVpcEndpoints());
            final var describeSubnetsResponse = ec2.describeSubnets();
            System.out.println(describeSubnetsResponse);
            var describeInstancesResponse = ec2.describeInstances();
            System.out.println(describeInstancesResponse);

        } catch (Ec2Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}